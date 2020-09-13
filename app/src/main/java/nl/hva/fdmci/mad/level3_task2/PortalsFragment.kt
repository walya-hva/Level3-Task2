package nl.hva.fdmci.mad.level3_task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddPortalResult()
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->

            val portalTitle = bundle.getString(BUNDLE_PORTAL_TITLE).toString()
            val portalUrl = bundle.getString(BUNDLE_PORTAL_URL).toString()

            val portal = Portal(portalTitle, portalUrl)

            portals.add(portal)
            portalAdapter.notifyDataSetChanged()
        }
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvPortals.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        rvPortals.adapter = portalAdapter

        //createItemTouchHelper().attachToRecyclerView(rvPortals) TODO: remove or change
    }
}