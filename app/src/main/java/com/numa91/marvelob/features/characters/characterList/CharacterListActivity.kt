package com.numa91.marvelob.features.characters.characterList

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.numa91.marvelob.R
import com.numa91.marvelob.databinding.ActivityCharacterListBinding
import com.numa91.marvelob.extensions.isNetworkAvailable
import com.numa91.marvelob.features.characters.characterDetail.CharacterDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class CharacterListActivity : AppCompatActivity() {

    private lateinit var activityCharacterListBinding: ActivityCharacterListBinding
    private lateinit var mAdapter: CharactersAdapter
    private val characterListViewModel: CharacterListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCharacterListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_character_list)
        mAdapter = CharactersAdapter { characterModel -> goToDetailsActivity(characterModel.id) }

        activityCharacterListBinding.charactersRecyclerView.adapter = mAdapter

        if (isNetworkAvailable()) {
            characterListViewModel.getCharacters()
        } else {
            Toast.makeText(
                this,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        with(characterListViewModel) {
            charactersData.observe(this@CharacterListActivity, Observer {
                mAdapter?.charactersList = it.data.results
            })

            showProgressbar.observe(this@CharacterListActivity, Observer { isVisible ->
                activityCharacterListBinding.progressBar.visibility =
                    if (isVisible) VISIBLE else GONE
            })

            errorMsg.observe(this@CharacterListActivity, Observer {
                Toast.makeText(this@CharacterListActivity, it, LENGTH_LONG).show()
            })
        }
    }

    private fun goToDetailsActivity(id: Long) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(CHARACTER_ID, id)
        startActivity(intent)
    }

    companion object{
        const val CHARACTER_ID = "character_id"
    }
}