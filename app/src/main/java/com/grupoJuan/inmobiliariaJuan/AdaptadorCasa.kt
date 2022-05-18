package com.grupoJuan.inmobiliariaJuan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class
 AdaptadorCasa(private val context: Context,
                    private val dataSource: ArrayList<Casa>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        // 1
        if (convertView == null) {

            // 2
            view = inflater.inflate(R.layout.list_item_recipe, parent, false)

            // 3
            holder = ViewHolder()
            holder.thumbnailImageView1 = view.findViewById(R.id.recipe_list_thumbnail) as ImageView
            holder.titleTextView1 = view.findViewById(R.id.recipe_list_title) as TextView
            holder.subtitleTextView1 = view.findViewById(R.id.recipe_list_subtitle) as TextView
            holder.detailTextView1 = view.findViewById(R.id.recipe_list_detail) as TextView

            // 4
            view.tag = holder
        } else {
            // 5
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        // 6
        val titleTextView1 = holder.titleTextView1
        val subtitleTextView1 = holder.subtitleTextView1
        val detailTextView1 = holder.detailTextView1
        val thumbnailImageView1 = holder.thumbnailImageView1


        val recipe = getItem(position) as Casa

        titleTextView1.text = recipe.title
        subtitleTextView1.text = recipe.description
        detailTextView1.text = recipe.price


        Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView1)



        return view    }
    private class ViewHolder {
        lateinit var titleTextView1: TextView
        lateinit var subtitleTextView1: TextView
        lateinit var detailTextView1: TextView
        lateinit var thumbnailImageView1: ImageView
    }
   }