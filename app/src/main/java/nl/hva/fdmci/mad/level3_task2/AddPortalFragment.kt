package nl.hva.fdmci.mad.level3_task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*
import java.util.regex.Pattern


const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_TITLE = "bundle_portal_title"
const val BUNDLE_PORTAL_URL = "bundle_portal_url"
const val URL_PATTERN = "https?://[a-zA-Z0-9]*[.][a-zA-Z0-9]*"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddPortal.setOnClickListener {
            onAddPortal()
        }

    }

    private fun onAddPortal() {
        val portalTitle = etTitle.text.toString()
        val portalUrl = etUrl.text.toString()

        if (portalTitle.isNotBlank() && portalUrl.isNotBlank()
            && Pattern.matches(URL_PATTERN, portalUrl)) {
            //set the data as fragmentResult, we are listening for REQ_PORTAL_KEY in PortalsFragment!
            val bundle = bundleOf(Pair(BUNDLE_PORTAL_TITLE, portalTitle))
            bundle.putString(BUNDLE_PORTAL_URL, portalUrl)
            setFragmentResult(REQ_PORTAL_KEY, bundle)

            //"pop" the backstack, this means we destroy
            //this fragment and go back to the PortalsFragment
            findNavController().popBackStack()

        } else {
            Toast.makeText(
                activity,
                R.string.invalid, Toast.LENGTH_SHORT
            ).show()
        }
    }
}