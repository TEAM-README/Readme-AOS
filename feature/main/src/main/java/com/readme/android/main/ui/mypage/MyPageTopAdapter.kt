package com.readme.android.main.ui.mypage

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.readme.android.domain.entity.MyPageUser
import com.readme.android.main.databinding.LayoutMyPageTopBinding

class MyPageTopAdapter(private val myPageUser: MyPageUser) :
    RecyclerView.Adapter<MyPageTopAdapter.MyPageTopViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageTopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutMyPageTopBinding.inflate(inflater, parent, false)
        return MyPageTopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageTopViewHolder, position: Int) {
        holder.onBind(myPageUser)
    }

    override fun getItemCount() = 1

    class MyPageTopViewHolder(
        private val binding: LayoutMyPageTopBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(myPageUser: MyPageUser) {
            binding.userData = myPageUser

            binding.btnSetting.setOnClickListener {
                with(itemView.context) {
                    startActivity(Intent(this, MyPageSettingActivity::class.java))
                }
            }
        }
    }
}
