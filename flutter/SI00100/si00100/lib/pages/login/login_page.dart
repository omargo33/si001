import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:si00100/pages/login/widget/login_form.dart';
import 'package:si00100/widget/hx_widget.dart';
import 'package:localization/localization.dart';

/// Clase para el login
///
///
class LogingPage extends StatefulWidget {
  const LogingPage({super.key});

  @override
  _LogingPageState createState() => _LogingPageState();
}

class _LogingPageState extends State<LogingPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
          child: SizedBox(
              width: 320,
              child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    const SizedBox(height: 50),
                    SvgPicture.asset(
                      'images_svg_login'.i18n(),
                      height: 180,
                      width: 180,
                    ),
                    const SizedBox(height: 20),
                    HxWidget(indice: 0, texto: 'login_title'.i18n()),
                    //HxWidget(indice: 4, texto: 'login_subtitle'.i18n()),
                    const SizedBox(height: 20),
                    const LoginForm(),
                  ]))),
    );
  }
}
