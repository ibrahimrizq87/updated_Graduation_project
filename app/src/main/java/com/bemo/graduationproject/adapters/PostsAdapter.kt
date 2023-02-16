package com.bemo.graduationproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.R
import com.bemo.graduationproject.databinding.ActivityHomeScreenBinding


    class PostsAdapter(
                       val context: Context,
                       var postsList:MutableList<Posts>,
                       val onItemClicked:(Int,Posts) ->Unit,
                       val onDeleteClicked:(Int,Posts) ->Unit,
                       val onUpdateClicked:(Int,Posts) ->Unit
    )
: RecyclerView.Adapter<PostsAdapter.myViewHolder>() {
    /*private lateinit var onItemClick: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(pos:Int)
        fun onUpdateClick(pos:Int)
        fun onDeleteClick(pos:Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        onItemClick=listener
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false)
        //return myViewHolder(view,onItemClick)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = postsList[position]
        holder.text1.text = currentItem.description
        holder.text2.text = currentItem.authorName


    }
fun update(list: MutableList<Posts>){
    this.postsList=list
notifyDataSetChanged()
}
        fun deleteItem(item: Int){
            postsList.removeAt(item)
            notifyItemChanged(item)
        }



        override fun getItemCount(): Int {
        return postsList.size
    }
inner    class myViewHolder(item: View) :RecyclerView.ViewHolder(item){

        val text1 = item.findViewById<TextView>(R.id.txt1)
        val text2 = item.findViewById<TextView>(R.id.txt2)

        val delete = item.findViewById<Button>(R.id.delete)
        val updatebt = item.findViewById<Button>(R.id.update)
        val recyItem = item.findViewById<ConstraintLayout>(R.id.item_layout)
init {
    recyItem.setOnClickListener {
     onItemClicked.invoke(adapterPosition,postsList[adapterPosition])
    }
    updatebt.setOnClickListener {
        onUpdateClicked.invoke(adapterPosition,postsList[adapterPosition])
    }
    delete.setOnClickListener {
        onDeleteClicked.invoke(adapterPosition,postsList[adapterPosition])
    }
}


    }

}