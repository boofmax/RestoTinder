package com.mobdeve.deculawan.practice_fakeadex_providedfiles

import android.os.Bundle
import android.widget.AbsListView.RecyclerListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.mobdeve.deculawan.practice_fakeadex_providedfiles.ui.theme.PracticeFakeadexProvidedFilesTheme

class MainActivity : ComponentActivity() {
    //load data
    private val pokeDex: ArrayList<PokemonModel> = DataGenerator.loadData()

    //Our RecyclerView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.recyclerView = findViewById(R.id.recyclerView)
        this.recyclerView.adapter = MyAdapter(this.pokeDex)

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        this.recyclerView.layoutManager = layoutManager

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(this.recyclerView)

    }
}