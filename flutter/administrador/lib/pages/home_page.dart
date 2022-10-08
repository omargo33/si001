import 'package:administrador/common/app_colors.dart';
import 'package:administrador/pages/dashboard/dashboard.dart';
import 'package:administrador/pages/widget/side_bar_menu.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.bgSideMenu,
      body: SafeArea(
          child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          /// Side navigation menu
          Expanded(
            child: SideBarMenu(),
          ),

          /// Main body part
          Expanded(flex: 4, child: DashboardPage()),
        ],
      )),
    );
  }
}
