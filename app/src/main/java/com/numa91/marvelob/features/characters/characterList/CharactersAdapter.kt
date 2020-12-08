package com.numa91.marvelob.features.characters.characterList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.numa91.marvelob.R
import com.numa91.marvelob.databinding.HolderCharacterBinding
import com.numa91.marvelob.domain.model.CharacterModel
import com.numa91.marvelob.extensions.secureUrl
import kotlin.properties.Delegates

class CharactersAdapter(val itemClick:(CharacterModel) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var charactersList: List<CharacterModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderCharacterBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_character, parent, false
        )
        return CharacterViewHolder(holderCharacterBinding)

    }

    override fun getItemCount(): Int =
        if (charactersList.isNullOrEmpty()) 0 else charactersList.size

    private fun getItem(pos: Int): CharacterModel = charactersList[pos]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CharacterViewHolder).onBind(getItem(position))
        holder.itemView.setOnClickListener { itemClick(getItem(position)) }
    }


    private inner class CharacterViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(characterModel: CharacterModel) {
            (dataBinding as HolderCharacterBinding).character = characterModel
        }
    }

    companion object{
        @BindingAdapter("imageUrl","imageExtension")
        @JvmStatic
        fun loadImage(view: ImageView, url: String, imageExtension: String){
            Glide.with(view.context).load("$url.$imageExtension".secureUrl()).error(android.R.drawable.ic_menu_close_clear_cancel).into(view)
        }
    }
}