package com.androidhuman.example.simplecontacts

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.androidhuman.example.simplecontacts.model.Person
import com.androidhuman.example.simplecontacts.model.Person.PRIMARY_KEY
import kotlinx.android.synthetic.main.item_person.view.*

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PersonHolder>() {

    private var people: List<Person>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        return PersonHolder(parent)
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        val person = people!![position]

        with(holder.itemView) {
            tv_item_person_name.text = person.name
            tv_item_person_address.text =
                    if (!TextUtils.isEmpty(person.address)) person.address else "(No address)"

            setOnClickListener {
                with(holder.itemView.context) {
                    startActivity(Intent(this, EditActivity::class.java)
                            .putExtra(PRIMARY_KEY, person.id))
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return people!![position].id
    }

    override fun getItemCount(): Int {
        return people?.size ?: 0
    }

    fun setPeople(people: List<Person>?) {
        this.people = people
    }

    inner class PersonHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_person, parent, false))

}
