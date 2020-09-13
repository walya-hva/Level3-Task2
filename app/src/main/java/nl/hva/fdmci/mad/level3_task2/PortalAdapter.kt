package nl.hva.fdmci.mad.level3_task2

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalAdapter(private val portals: List<Portal>) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(portal: Portal) {
            itemView.tvPortalTitle.text = portal.portalName
            itemView.tvPortalLink.text = portal.portalUrl

            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
            builder.addDefaultShareMenuItem()
            builder.setShowTitle(true)
            builder.setExitAnimations(itemView.context, android.R.anim.fade_in, android.R.anim.fade_out)

            builder.build()
            itemView.setOnClickListener {
                builder.build().launchUrl(itemView.context, Uri.parse(portal.portalUrl))
            }
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return portals.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portals[position])
    }

}