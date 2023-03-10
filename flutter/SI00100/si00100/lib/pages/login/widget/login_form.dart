import 'package:flutter/material.dart';
import 'package:flutter_form_builder/flutter_form_builder.dart';
import 'package:localization/localization.dart';
import 'package:si00100/controller/login_controller.dart';
import 'package:si00100/pages/widgets.dart';
import 'package:si00100/widget/hx_widget.dart';
import 'package:form_builder_validators/form_builder_validators.dart';

/// Clase para el formulario de login
///
class LoginForm extends StatefulWidget {
  const LoginForm({Key? key}) : super(key: key);

  @override
  State<LoginForm> createState() => _LoginForm();
}

/// Button de login
class _LoginForm extends State<LoginForm> {
  int _formKey = 1;

  final AlertDialogWidget _alertDialogWidget = const AlertDialogWidget();

  static const int _formKeyLogin = 0;
  static const int _formKeyRecobrar = 2;

  /// Formulario de login
  final _formLoginBuilderKey = GlobalKey<FormBuilderState>();
  final _userFieldKey = GlobalKey<FormBuilderFieldState>();
  final _passwordFieldKey = GlobalKey<FormBuilderFieldState>();

  /// Formulario de recuperar clave
  final _formRecoveryPassBuilderKey = GlobalKey<FormBuilderState>();
  final _emailFieldKey = GlobalKey<FormBuilderFieldState>();

  late bool _passwordVisible;

  @override
  void initState() {
    super.initState();
    _formKey = _formKeyLogin;
    _passwordVisible = false;
  }

  /// Evento de recuperar clave
  ///
  void _onPressedRecuperarClave() {
    _alertDialogWidget.info(context, 'login_msg_recuperar_0'.i18n(),
        'login_msg_recuperar_1'.i18n(), "images_svg_mail".i18n());
  }

  /// Event login
  /// consume rest
  /// delete data login
  /// send to dahsboard
  _onPressedLogin() async {
    LoginController loginController = LoginController();
    await loginController.login(context, _userFieldKey.currentState?.value,
        _passwordFieldKey.currentState?.value);

    if (loginController.isLogin) {
      _userFieldKey.currentState?.reset();
      _userFieldKey.currentState?.setValue(null);

      _passwordFieldKey.currentState?.reset();
      _passwordFieldKey.currentState?.setValue(null);
    } else {
      print("is login b");
      print(loginController.isLogin);
      //TODO: ir a dashboard
      // Si es correcto y no hay que cambiar la clave
      // Navigator.pushNamed(context, '/home');
    }
  }

  /// Formulario de recuperar clave
  _formRecuperar() {
    return FormBuilder(
      key: _formRecoveryPassBuilderKey,
      autovalidateMode: AutovalidateMode.onUserInteraction,
      skipDisabled: false,
      child: Column(
        children: [
          HxWidget(indice: 4, texto: "login_recuperar_clave".i18n()),
          const SizedBox(height: 10),
          FormBuilderTextField(
            key: _emailFieldKey,
            name: 'emal_field',
            enabled: true,
            decoration: InputDecoration(
              labelText: 'login_email_label'.i18n(),
              hintText: 'login_email_hint'.i18n(),
            ),
            validator: FormBuilderValidators.compose([
              FormBuilderValidators.required(
                  errorText: 'validation_required'.i18n()),
              FormBuilderValidators.email(errorText: 'validation_email'.i18n()),
            ]),
          ),
          const SizedBox(height: 20),
          ElevatedButton(
              onPressed: _onPressedRecuperarClave,
              child: Text('login_event_recuperar_clave'.i18n())),
          const SizedBox(height: 20),
          TextButton(
              onPressed: () => {
                    setState(() {
                      _formKey = _formKeyLogin;
                    })
                  },
              child: Text(
                'event_cancel'.i18n(),
              )),
        ],
      ),
    );
  }

  /// Formulario de login
  _formLogin() {
    return Column(
      children: [
        HxWidget(indice: 4, texto: "login_acceso".i18n()),
        // Formulario
        FormBuilder(
          key: _formLoginBuilderKey,
          autovalidateMode: AutovalidateMode.onUserInteraction,
          skipDisabled: false,
          child: Column(
            mainAxisSize: MainAxisSize.min,
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              // login_username
              FormBuilderTextField(
                key: _userFieldKey,
                name: 'username',
                enabled: true,
                decoration: InputDecoration(
                  labelText: 'login_username_label'.i18n(),
                  hintText: 'login_username_hint'.i18n(),
                ),
                validator: FormBuilderValidators.compose([
                  FormBuilderValidators.required(
                      errorText: 'validation_required'.i18n()),
                ]),
              ),
              const SizedBox(height: 10),
              FormBuilderTextField(
                key: _passwordFieldKey,
                name: 'password',
                enabled: true,
                obscureText: !_passwordVisible,
                decoration: InputDecoration(
                  labelText: 'login_password_label'.i18n(),
                  hintText: 'login_password_hint'.i18n(),
                  suffixIcon: IconButton(
                    icon: Icon(
                      _passwordVisible
                          ? Icons.visibility
                          : Icons.visibility_off,
                    ),
                    onPressed: () {
                      setState(() {
                        _passwordVisible = !_passwordVisible;
                      });
                    },
                  ),
                ),
                validator: FormBuilderValidators.compose([
                  FormBuilderValidators.required(
                      errorText: 'validation_required'.i18n()),
                ]),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                  onPressed: () => {
                        _formLoginBuilderKey.currentState?.validate() == true
                            ? _onPressedLogin()
                            : {},
                      },
                  child: Text('login_event_ingresar'.i18n())),
            ],
          ),
        ),
        const SizedBox(height: 10),
        TextButton(
          onPressed: () => {
            setState(() {
              _formKey = _formKeyRecobrar;
            })
          },
          child: Text('login_event_recuperar'.i18n()),
        ),
      ],
    );
  }

  /// build formulario de login
  @override
  Widget build(BuildContext context) {
    switch (_formKey) {
      case _formKeyLogin:
        return _formLogin();
      case _formKeyRecobrar:
        return _formRecuperar();
      default:
        return _formLogin();
    }
  }
}
