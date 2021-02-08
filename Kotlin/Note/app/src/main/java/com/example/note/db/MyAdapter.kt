package com.example.note.db

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.EditActivity
import com.example.note.R
import kotlin.contracts.contract

class MyAdapter(listMain: ArrayList<ListItem>, contextM: Context) :
        RecyclerView.Adapter<MyAdapter.MyHolder>() {

    val listArray = listMain
    val context = contextM

    class MyHolder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvImage = itemView.findViewById<ImageView>(R.id.image)
        val context = contextV

        fun setData(item: ListItem){

            tvTitle.text = item.title
            tvImage.setImageURI(Uri.parse(item.uri.toString()))

            itemView.setOnClickListener{

                val intent = Intent(context, EditActivity::class.java).apply {

                    putExtra(BaseColumns._ID, item.id)
                    putExtra(IntentConstants.I_TITLE_KEY, item.title)
                    putExtra(IntentConstants.I_CONTENT_KEY, item.content)
                    putExtra(IntentConstants.I_URI_KEY, item.uri)

                }
                context.startActivity(intent)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val inflater = LayoutInflater.from(parent.context)
        return MyHolder(inflater.inflate(R.layout.rc_item, parent, false), context)

    }

    override fun getItemCount(): Int {

        return listArray.size

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.setData(listArray[position])

    }

    fun updateAdapter(listItems: List<ListItem>){

        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()

    }

    fun removeItem(pos: Int, dbManager: MyDbManager){

        dbManager.removeItemFromDb(listArray[pos].id.toString())
        listArray.removeAt(pos)
        notifyItemRangeChanged(0, listArray.size)
        notifyItemRemoved(pos)

    }
}