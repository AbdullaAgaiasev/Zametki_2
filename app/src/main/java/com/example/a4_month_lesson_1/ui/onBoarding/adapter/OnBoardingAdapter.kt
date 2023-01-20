package com.example.a4_month_lesson_1.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a4_month_lesson_1.databinding.ItemOnboardingBinding
import com.example.a4_month_lesson_1.model.OnBoard
import com.example.a4_month_lesson_1.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit):
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val data = arrayListOf(
        OnBoard("Добро пожаловать", "Добро пожаловать в наше приложение, в нашем приложении вы сможете написать и сохранить любую информацию.","https://images.pexels.com/photos/1629212/pexels-photo-1629212.jpeg?cs=srgb&dl=pexels-min-an-1629212.jpg&fm=jpg"),
        OnBoard("Обновление", "На данный момент наше приложение в стадии разроботки поэтому ожидайте новых обновлений.","https://roadgid.kz/wp-content/uploads/2016/08/website-update.jpg"),
        OnBoard("До скорой встречи", "Надеюсь у вас не возникнет никаких проблем при использовании нашего приложения.","https://www.meme-arsenal.com/memes/0a9871cafd3e0accdc93dbfbf1198068.jpg")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return  data.size
    }
    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding): ViewHolder(binding.root){
        fun bind(onBoard: OnBoard) {
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.desc
            binding.image.loadImage(onBoard.image.toString())
            binding.btnStart.isVisible = adapterPosition == data.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }
        }

    }
}