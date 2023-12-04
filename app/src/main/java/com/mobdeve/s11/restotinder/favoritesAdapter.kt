package com.mobdeve.s11.restotinder

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class favoritesAdapter (private val data: ArrayList<RestaurantModel>): RecyclerView.Adapter<MyViewHolder>() {
    companion object {
        private const val TAG = "favoritesAdapter"
    }

    private lateinit var activity: Activity
    private lateinit var dbRef: FirebaseFirestore
    private lateinit var username: String
    private lateinit var favoriteRestos :ArrayList<RestaurantModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout_favorites, parent, false)
        dbRef = Firebase.firestore

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun delete_favorite(collectionReference: CollectionReference, usernameToDelete: String, imageIdToDelete: String?) {
        val query = collectionReference
            .whereEqualTo("username", usernameToDelete)
            .whereEqualTo("imageId", imageIdToDelete)
        query.get().addOnSuccessListener { documents ->
            for (document in documents) {
                collectionReference.document(document.id).delete()
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data.get(position))

        holder.setDeleteOnClickListener(View.OnClickListener {
            val favoritedObjectInfo = data[holder.bindingAdapterPosition]
            val collectionReference = dbRef.collection(MyFirestoreReferences.FAVORITE_COLLECTION)

            // Remove from UI
            data.removeAt(holder.bindingAdapterPosition)
            notifyItemRemoved(holder.bindingAdapterPosition)

            // Remove from Firestore
            val query = collectionReference
                .whereEqualTo("username", username)
                .whereEqualTo("imageId", favoritedObjectInfo.imageId)

            query.get().addOnSuccessListener { documents ->
                for (document in documents) {
                    collectionReference.document(document.id).delete()
                }
            }.addOnFailureListener {
                // Handle deletion failure, show a message, or log an error
                Log.e(TAG, "Failed to delete document: ${it.message}")
            }
        })

        holder.setMapOnClickListener(View.OnClickListener {
            Log.d("MyViewHolder", "San ba talaga here")
            val intent = Intent(holder.itemView.context, MapsActivity::class.java)
            intent.putExtra("restaurantLatitude", data[position].latitude)
            intent.putExtra("restaurantLongitude", data[position].longitude)
            holder.itemView.context.startActivity(intent)
        }, data[position])

        holder.setBackButtonOnClickListener(View.OnClickListener {
            this.activity.finish()
        })

    }
    fun setUsername(username: String) {
        this.username = username
    }

    fun setActivity(activity: Activity) {
        this.activity = activity
    }
}