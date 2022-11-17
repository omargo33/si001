import 'package:flutter/material.dart';
import 'package:si00100/common/app_colors.dart';

class InputWidget extends StatelessWidget {
  static String tipoInput = 'input';
  static String tipoPassword = 'password';

  final String label;
  final String? contratexto;
  final String? tipo;

  const InputWidget({
    Key? key,
    required this.label,
    this.contratexto,
    this.tipo,
  }) : super(key: key);

  /// textfield base
  TextField buildTextField() {
    return TextField(
      decoration: InputDecoration(
        hintText: label,
        counterText: contratexto,
        filled: true,
        fillColor: AppColors.spaceCadetMC[50],
        labelStyle: const TextStyle(fontSize: 12),
        contentPadding: const EdgeInsets.only(left: 30),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.silver),
          borderRadius: BorderRadius.circular(15),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.blackCoralMC),
          borderRadius: BorderRadius.circular(15),
        ),
      ),
    );
  }

  /// textfield base
  TextField buildPassword() {
    return TextField(
      obscureText: true,
      decoration: InputDecoration(
        hintText: label,
        counterText: contratexto,
        suffixIcon: Icon(
          Icons.visibility_off_outlined,
          color: AppColors.mandarinMC,
        ),
        filled: true,
        fillColor: AppColors.spaceCadetMC[50],
        labelStyle: const TextStyle(fontSize: 12),
        contentPadding: const EdgeInsets.only(left: 30),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.silver),
          borderRadius: BorderRadius.circular(15),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.blackCoralMC),
          borderRadius: BorderRadius.circular(15),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    // tipo es nulo
    if (tipo == null) {
      return buildTextField();
    } else {
      // tipo es password
      if (tipo == tipoInput) {
        return buildTextField();
      } else {
        // tipo es password
        return buildPassword();
      }
    }
  }
}
