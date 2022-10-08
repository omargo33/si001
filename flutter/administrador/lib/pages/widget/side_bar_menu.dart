import 'package:administrador/common/app_colors.dart';
import 'package:flutter/material.dart';

class SideBarMenu extends StatefulWidget {
  @override
  _SideBarMenuState createState() => _SideBarMenuState();
}

class _SideBarMenuState extends State<SideBarMenu> {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      elevation: 0,
      child: Container(
        color: AppColors.bgSideMenu,
        child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          Container(
            margin: EdgeInsets.symmetric(horizontal: 20, vertical: 20),
            child: Text(
              "Matriz Clientes",
              style: TextStyle(
                color: AppColors.yellow,
                fontSize: 25,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          DrawerListTile(
            title: "Dashboard",
            icon: "menu_home.png",
            press: () {},
          ),
          DrawerListTile(
            title: "Settings",
            icon: "menu_settings.png",
            press: () {},
          ),
          Spacer(),
          Image.asset("sidebar_image.png"),
        ]),
      ),
    );
  }
}

class DrawerListTile extends StatelessWidget {
  final String title, icon;
  final VoidCallback press;

  const DrawerListTile(
      {Key? key, required this.title, required this.icon, required this.press})
      : super(key: key);
  @override
  Widget build(BuildContext context) {
    return ListTile(
      onTap: press,
      horizontalTitleGap: 0.0,
      leading: Image.asset(
        icon,
        color: AppColors.white,
        height: 16,
      ),
      title: Text(
        title,
        style: TextStyle(color: AppColors.white),
      ),
    );
  }
}
