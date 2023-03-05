import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:si00100/pages/login/widget/login_form.dart';
import 'package:si00100/widget/hx_widget.dart';
import 'package:localization/localization.dart';

// Clase para el login
class LoginPage extends StatefulWidget {
  static const String routeName = 'login_page';

  const LoginPage({super.key});

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
          child: SizedBox(
              width: 320,
              child: Column(
                  mainAxisSize: MainAxisSize.max,
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Column(
                      mainAxisSize: MainAxisSize.min,
                      children: <Widget>[
                        const SizedBox(height: 50),
                        SvgPicture.asset(
                          'images_svg_login'.i18n(),
                          height: 180,
                          width: 180,
                        ),
                        const SizedBox(height: 20),
                        HxWidget(indice: 0, texto: 'login_title'.i18n()),
                        const LoginForm(),
                      ],
                    ),
                    Row(
                      mainAxisSize: MainAxisSize.max,
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: <Widget>[
                        HxWidget(indice: 4, texto: 'from'.i18n()),
                        const SizedBox(
                          width: 4,
                        ),
                        HxWidget(indice: 2, texto: 'business'.i18n()),
                      ],
                    ),
                  ]))),
    );
  }
}
