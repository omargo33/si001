import 'package:flutter/material.dart';
import 'package:si00100/pages/login/login_page.dart';
import 'package:si00100/sharePreferences/preferences.dart';
import 'package:localization/localization.dart';

// menu lateral
class SideMenu extends StatelessWidget {
  const SideMenu({super.key});

//TODO: implementar el menu lateral
  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: ListView(
      padding: EdgeInsets.zero,
      children: <Widget>[
        const DrawerHeader(
          decoration: BoxDecoration(
            color: Colors.blue,
          ),
          child: Text('Drawer Header'),
        ),
        //TODO implementar el menu lateral desde un rest api de permisos
        ListTile(
          title: const Text('Item 1'),
          onTap: () {
            // Update the state of the app.
            // ...
            // Then close the drawer.
            Navigator.pop(context);
          },
        ),
        ListTile(
          title: const Text('Item 2'),
          onTap: () {
            // Update the state of the app.
            // ...
            // Then close the drawer.
            Navigator.pop(context);
          },
        ),
        // close session
        // todo: validad function
        ListTile(
          title: Text('side_menu_close_session'.i18n()),
          onTap: () {
            Preferences.setIsLogin = false;
            Preferences.setToken = '';
            Preferences.setTokenRefresh = '';
            Preferences.setUser = '';
            Navigator.pushReplacementNamed(context, LoginPage.routeName);
          },
        ),
      ],
    ));
  }
}
