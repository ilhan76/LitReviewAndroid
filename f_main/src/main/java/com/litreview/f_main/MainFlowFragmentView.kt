package com.litreview.f_main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.mvi.BaseFragment
import com.litreview.f_main.databinding.FlowFragmentMainBinding
import com.litreview.i_navigation.tabsNavigation.BottomTab
import com.litreview.i_navigation.tabsNavigation.TabsNavigationEventHub
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.i_navigation.open
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFlowFragmentView :
    BaseFragment<MainFlowState, MainFlowEvent>(R.layout.flow_fragment_main) {

    override val viewModel by viewModels<MainFlowViewModel>()
    private val vb by viewBinding(FlowFragmentMainBinding::bind)

    @Inject
    lateinit var ch: MainFlowCommandHolder

    @Inject
    lateinit var tabsNavigationEventHub: TabsNavigationEventHub

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController =
            (childFragmentManager.findFragmentById(R.id.main_container_view) as NavHostFragment).navController
        NavigationUI.setupWithNavController(vb.bottomNavBar, navController)
        viewModel.bindFlow()
        bind()
    }

    private fun bind() {
        ch.openScreen.flow bindTo {
            findNavControllerSafely()?.open(it)
        }
        tabsNavigationEventHub.flow bindTo {
            vb.bottomNavBar.selectedItemId = when (it) {
                BottomTab.FEED -> R.id.feedFragmentView
                BottomTab.SEARCH -> R.id.searchFragmentView
                BottomTab.CHATS -> R.id.chatsFragmentView
                BottomTab.PROFILE -> R.id.profileFragmentView
            }
        }
    }
}