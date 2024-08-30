package com.cenkeraydin.kekodchallengeone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.cenkeraydin.kekodchallengeone.R
import com.cenkeraydin.kekodchallengeone.databinding.ActivityMainBinding
import com.cenkeraydin.kekodchallengeone.databinding.FragmentEgoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class EgoFragment : Fragment() {

    private var _binding: FragmentEgoBinding? = null
    private val binding get() = _binding!!

    private lateinit var swEgo: SwitchCompat
    private lateinit var switches: List<SwitchCompat>
    private lateinit var bottomNavigationView: BottomNavigationView
    private val toggleOrder = mutableListOf<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEgoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swEgo = binding.swEgo

        switches = listOf(
            binding.swHappiness,
            binding.swOptimisim,
            binding.swKindness,
            binding.swGiving,
            binding.swRespect
        )

        val mainBinding = ActivityMainBinding.bind(requireActivity().findViewById(R.id.main))
        bottomNavigationView = mainBinding.bottomNav

        bottomNavigationView.visibility = if (swEgo.isChecked) View.GONE else View.VISIBLE

        switches.forEach { switch ->
            switch.isEnabled = !swEgo.isChecked
            switch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (!toggleOrder.contains(switch.id)) {
                        toggleOrder.add(switch.id)
                    }
                } else {
                    toggleOrder.remove(switch.id)
                }
                updateBottomNavigationMenu()
            }
        }


        swEgo.setOnCheckedChangeListener { _, isChecked ->
            switches.forEach { switch ->
                switch.isEnabled = !isChecked
                if (isChecked) switch.isChecked = false
            }
            bottomNavigationView.visibility = if (isChecked) View.GONE else View.VISIBLE
        }
    }


    private fun updateBottomNavigationMenu() {
        bottomNavigationView.menu.clear()
        bottomNavigationView.menu.add(Menu.NONE, R.id.egoFragment, Menu.NONE, "Home").setIcon(R.drawable.baseline_home_24)

        val checkedSwitches = switches.filter { it.isChecked }
        val sortedSwitchesToAdd = checkedSwitches.sortedBy { toggleOrder.indexOf(it.id) }

        sortedSwitchesToAdd.forEach { switch ->
            if (bottomNavigationView.menu.size() >= 5) {
                Snackbar.make(
                    binding.root,
                    "Maximum number of items reached",
                    Snackbar.LENGTH_SHORT
                ).show()
                return
            }
            val title = when (switch.id) {
                R.id.swHappiness -> "Happiness"
                R.id.swOptimisim -> "Optimism"
                R.id.swKindness -> "Kindness"
                R.id.swGiving -> "Giving"
                R.id.swRespect -> "Respect"
                else -> "Switch"
            }
            val icon = when (switch.id) {
                R.id.swHappiness -> R.drawable.happy
                R.id.swOptimisim -> R.drawable.optimism
                R.id.swKindness -> R.drawable.kindness
                R.id.swGiving -> R.drawable.giving
                R.id.swRespect -> R.drawable.respect
                else -> R.drawable.baseline_home_24
            }
            bottomNavigationView.menu.add(Menu.NONE, switch.id, Menu.NONE, title).setIcon(icon)
        }
    }


}