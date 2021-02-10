package com.androiddevs.tasklist.data.other

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.tasklist.R
import com.androiddevs.tasklist.data.db.entities.TaskItem
import com.androiddevs.tasklist.data.ui.tasklist.AddActivity
import com.androiddevs.tasklist.data.ui.tasklist.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.task_item.*
import kotlinx.android.synthetic.main.task_item.view.*
import java.text.SimpleDateFormat

class TaskAdapter(
    var taskList: List<TaskItem>,
    var viewModel: TaskViewModel,
    var context: Context,
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,
        parent, false)
        val holder =  TaskViewHolder(view)
        //setToolsDelete(holder)
        return holder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val curTask = taskList[position]

        val sdf = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")

        holder.itemView.tvTitle.text = curTask.title
        holder.itemView.cbIsDone.isChecked = curTask.isDone
        holder.itemView.tv_task_desc.text = (curTask.description.substringBefore(" ") + "...")
        holder.itemView.tv_task_Date.text = sdf.format(curTask.date)


        holder.itemView.setOnClickListener{

            val intent = Intent(context, AddActivity::class.java).apply {
                putExtra(Constants.FROM_KEY, Constants.TASK_FRAGMENT)
                putExtra(Constants.NEW, false)
                putExtra(Constants.ID, curTask.id)
                putExtra(Constants.DESCRIPTION_KEY, curTask.description)
                putExtra(Constants.TITLE_KEY, curTask.title)
                putExtra(Constants.IS_DONE, curTask.isDone)
            }
            context.startActivity(intent)
        }

        holder.itemView.cbIsDone.setOnClickListener {

            val task = TaskItem(curTask.title,curTask.description,
                holder.itemView.cbIsDone.isChecked, curTask.date)

            curTask.id?.let { it1 -> viewModel.deleteById(it1) }
            viewModel.insert(task)
        }

        holder.itemView.ib_delete.setOnClickListener{
            curTask.id?.let { it1 -> viewModel.deleteById(it1) }
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }


    /*private fun setToolsDelete(holder: TaskViewHolder){

        btn_delete.setOnClickListener{
            btn_delete.visibility = View.GONE
            btn_delete_back.visibility = View.VISIBLE
            holder.itemView.ib_delete.visibility = View.VISIBLE
            holder.itemView.cbIsDone.visibility = View.GONE
            notifyDataSetChanged()
            Toast.makeText(context, holder.itemView.id.toString(), Toast.LENGTH_SHORT).show()
        }

        btn_delete_back.ib_tools_delete_back.setOnClickListener{
            btn_delete.visibility = View.VISIBLE
            btn_delete_back.visibility = View.GONE
            holder.itemView.ib_delete.visibility = View.GONE
            holder.itemView.cbIsDone.visibility = View.VISIBLE
            notifyDataSetChanged()
        }
    }*/



    inner class TaskViewHolder(taskView: View): RecyclerView.ViewHolder(taskView)
}