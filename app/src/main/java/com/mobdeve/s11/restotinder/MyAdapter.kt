package com.mobdeve.s11.restotinder

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyAdapter(private val data: ArrayList<RestaurantModel>): RecyclerView.Adapter<MyViewHolder>() {
    companion object {
        private const val TAG = "MyAdapter"
    }

    private lateinit var dbRef: FirebaseFirestore
    private lateinit var username: String
    private lateinit var favoriteRestos :ArrayList<RestaurantModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        dbRef = Firebase.firestore
        //favoriteRestos = loadFavoritesBlocking(dbRef.collection(MyFirestoreReferences.FAVORITE_COLLECTION))

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data.get(position))

        holder.setDeleteOnClickListener(View.OnClickListener {
            holder.itemView.setOnClickListener{
                Toast.makeText(holder.itemView.context, "Restaurant Deleted: "+
                data[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
            }

            this.data.removeAt(position)
            notifyDataSetChanged()
        })

        holder.setFavoriteOnClickListener(View.OnClickListener {
            Log.d(TAG, "Current Restaurants" + this.favoriteRestos.joinToString("\n"))
            val favorited_object_info = data[holder.getBindingAdapterPosition()]
            val collectionReference = dbRef.collection(MyFirestoreReferences.FAVORITE_COLLECTION)
            if(holder.isFavorite){
                Toast.makeText(
                    holder.itemView.context,
                    "Restaurant Added to Favorites: " + data[holder.adapterPosition].name,
                    Toast.LENGTH_SHORT
                ).show()

                /**?
                 * const val imageId_FIELD = "imageId"
                 *     const val isFavorite_FIELD = "isFavorite"
                 *     const val location_FIELD = "location"
                 *     const val name_FIELD = "name"
                 *     const val pricing_FIELD = "pricing"
                 *     const val rating_FIELD = "rating"
                 */
                collectionReference.add(this.populate_hashmap(favorited_object_info))
                this.favoriteRestos.add(favorited_object_info)
                Log.d(TAG, "Current Restaurants after addition" + this.favoriteRestos.joinToString("\n"))
                Log.d(TAG, "SUCC BALLS")//data[holder.getBindingAdapterPosition()])
                /*
                * Utilize update and get of firestore
                * Check how to call the update method.
                * Check how to call the delete method
                *
                * After loading Places API,
                * [1] Get Places API
                * [2] Check if user has existing records (Favorites)
                * [3] Compare if restorant is present in favorites
                *   - If present, isFavorite is True.
                * */
            } else {
                val existingFavorite = favoriteRestos.find {
                    it.name == favorited_object_info.name && it.location == favorited_object_info.location
                }
                Toast.makeText(
                    holder.itemView.context,
                    "Restaurant Removed from Favorites: " + data[holder.adapterPosition].name,
                    Toast.LENGTH_SHORT
                ).show()
                val imageIdToDelete = favorited_object_info.imageId
                val usernameToDelete = this.username
                this.delete_favorite(collectionReference, usernameToDelete, imageIdToDelete)
                this.favoriteRestos.remove(existingFavorite)
                Log.d(TAG, "Current Restaurants after removal" + this.favoriteRestos.joinToString("\n"))
                Log.d(TAG, "DEEZ NUTS")
            }
        })

        holder.setRatingOnClickListener(View.OnClickListener {
            val placeName = data[holder.adapterPosition].name

            // Check if Google Maps is installed on the device
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$placeName"))
            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(holder.itemView.context.packageManager) != null) {
                holder.itemView.context.startActivity(mapIntent)
            } else {
                Toast.makeText(
                    holder.itemView.context,
                    "Google Maps is not installed on your device.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        holder.setMapOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, MapsActivity::class.java)
            intent.putExtra("restaurantLatitude", data[position].latitude)
            intent.putExtra("restaurantLongitude", data[position].longitude)
            holder.itemView.context.startActivity(intent)
        }, data[position])

        holder.setProfileOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, FavoritesList::class.java)
            intent.putExtra("uname",this.username)
//            intent.putExtra("favoriteRestos", this.favoriteRestos)        // ISSUE: Depracated si getter.
            holder.itemView.context.startActivity(intent)

        })
    }
    fun setUsername(username: String) {
        this.username = username
    }

    fun setFavoriteRestos(favoriteRestos: ArrayList<RestaurantModel>) {
        this.favoriteRestos = favoriteRestos
    }

    fun populate_hashmap(favorited_object_info: RestaurantModel): MutableMap<String, Any?> {
        val favorited_object: MutableMap<String, Any?> = HashMap()
        favorited_object[MyFirestoreReferences.imageId_FIELD] = favorited_object_info.imageId
        favorited_object[MyFirestoreReferences.isFavorite_FIELD] = true
        favorited_object[MyFirestoreReferences.location_FIELD] = favorited_object_info.location
        favorited_object[MyFirestoreReferences.name_FIELD] = favorited_object_info.name
        favorited_object[MyFirestoreReferences.pricing_FIELD] = favorited_object_info.open
        favorited_object[MyFirestoreReferences.rating_FIELD] = favorited_object_info.rating
        favorited_object[MyFirestoreReferences.USER_FIELD] = this.username
        return favorited_object
    }
    fun delete_favorite(collectionReference : CollectionReference, usernameToDelete: String, imageIdToDelete: String){
        val query = collectionReference
            .whereEqualTo("imageId", imageIdToDelete)
            .whereEqualTo("username",usernameToDelete)

        query.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    // Delete the document(s) found by the query
                    document.reference.delete()
                        .addOnSuccessListener {
                            // Handle successful deletion
                            println("Document successfully deleted!")
                        }
                        .addOnFailureListener { e ->
                            // Handle deletion failure
                            println("Error deleting document: $e")
                        }
                }
            }
            .addOnFailureListener { e ->
                // Handle query failure
                println("Error querying documents: $e")
            }
    }

}