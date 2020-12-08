package com.numa91.marvelob.features.characters.characterDetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.numa91.marvelob.R
import com.numa91.marvelob.databinding.ActivityCharacterDetailBinding
import com.numa91.marvelob.extensions.isNetworkAvailable
import com.numa91.marvelob.extensions.secureUrl
import com.numa91.marvelob.features.characters.characterList.CharacterListActivity.Companion.CHARACTER_ID

import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailActivity: AppCompatActivity() {
    private lateinit var activityCharacterDetailBinding: ActivityCharacterDetailBinding
    private val characterDetailViewModel: CharacterDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCharacterDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        if (isNetworkAvailable()) {
            characterDetailViewModel.getCharacter(intent.getLongExtra(CHARACTER_ID,0L))
        } else {
            Toast.makeText(
                this,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        with(characterDetailViewModel) {
            characterData.observe(this@CharacterDetailActivity, Observer {
                it?.let {
                    activityCharacterDetailBinding.characterNameText.text = it.name
                    activityCharacterDetailBinding.characterDescriptionText.text = if (it.description.isNullOrEmpty()) getString(R.string.no_description) else it.description
                    Glide.with(this@CharacterDetailActivity)
                        .load("${it.thumbnail.path}.${it.thumbnail.extension}".secureUrl())
                        .error(android.R.drawable.ic_menu_close_clear_cancel)
                        .into(activityCharacterDetailBinding.characterImageView)
                }
            })

            showProgressbar.observe(this@CharacterDetailActivity, Observer { isVisible ->
                activityCharacterDetailBinding.progressBar.visibility =
                    if (isVisible) android.view.View.VISIBLE else android.view.View.GONE
            })

            errorMsg.observe(this@CharacterDetailActivity, Observer {
                Toast.makeText(this@CharacterDetailActivity, it,
                    Toast.LENGTH_LONG
                ).show()
            })
        }


    }
}