package appsinthesky.com.firebaseread.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import appsinthesky.com.firebaseread.R
import appsinthesky.com.firebaseread.model.Person

class PersonCardAdapter(context: Context, resource: Int, list: ArrayList<Person>): ArrayAdapter<Person>(context, resource, list) {

    private var mResource: Int = 0
    private var mList: ArrayList<Person>
    private var mLayoutInflater: LayoutInflater
    private var mContext: Context = context

    init{
        this.mResource = resource
        this.mList = list
        this.mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val returnView: View?
        if (convertView == null) {
            returnView = try {
                mLayoutInflater.inflate(mResource, null)

            } catch (e: Exception) {
                e.printStackTrace()
                View(context)
            }
            setUI(returnView, position)
            return returnView
        }
        setUI(convertView, position)
        return convertView
    }

    private fun setUI(view: View, position: Int) {
        val person: Person? = if(count > position) getItem(position) else null
        val firstName: TextView? = view.findViewById(R.id.person_card_first_name)
        firstName?.text = person?.firstName ?: ""


        val lastName: TextView? = view.findViewById(R.id.person_card_last_name)
        lastName?.text = person?.lastName ?: ""


        val skill: TextView? = view.findViewById(R.id.person_card_skill)
        skill?.text = person?.skill ?: ""

    }

}