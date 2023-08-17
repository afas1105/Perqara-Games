package com.example.perqaragames.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.perqaragames.R
import com.example.perqaragames.databinding.ActivityDetailBinding
import com.example.perqaragames.local.FavoriteData
import com.example.perqaragames.model.GameDetailResponse
import com.example.perqaragames.ui.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel by viewModels<FavoriteViewModel>()

    private val presenter by lazy { DetailPresenter(this) }

    private var idGame: Int? = 0

    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idGame = intent.getIntExtra("id", 0)
        presenter.getDetail(idGame ?: 0)

        binding.ivBack.setOnClickListener {
            finish()
        }
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkFavorite(idGame ?: 0)
            withContext(Dispatchers.Main) {
                Log.e("afas", "count = $count")
                isChecked = if (count > 0) {
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite_red_24)
                    true
                } else {
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite_border_24)
                    false
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onGetDetail(data: GameDetailResponse?) {
        binding.apply {
            Glide.with(root).load(data?.backgroundImage).into(ivGame)
            tvPublisher.text = data?.publishers?.get(0)?.name ?: ""
            tvNameGame.text = data?.name
            tvReleased.text = "Released date ${data?.released}"
            tvRating.text = data?.rating.toString()
            tvPlaytime.text = data?.playtime.toString()

            ivFavorite.setOnClickListener {
                isChecked = !isChecked
                if (isChecked){
                    viewModel.addToFavorites(
                        FavoriteData(
                            data?.id,
                            data?.name,
                            data?.backgroundImage,
                            data?.rating,
                            data?.released
                        )
                    )
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite_red_24)
                } else {
                    viewModel.removeFromFavorites(data?.id ?: 0)
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite_border_24)
                }
            }
        }
    }

    companion object {
        fun startActivity(context: Context, id: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }
}