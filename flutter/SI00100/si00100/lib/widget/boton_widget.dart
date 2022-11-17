import 'package:flutter/material.dart';
import 'package:si00100/common/app_colors.dart';

/// Clase boton widget primario
///
///
class BotonWidget extends StatelessWidget {
  final VoidCallback? onClick;
  final String texto;

  const BotonWidget({
    Key? key,
    this.onClick,
    required this.texto,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: onClick,
      style: ElevatedButton.styleFrom(
        onPrimary: AppColors.white,
        primary: AppColors.mandarinMC,
        padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 20),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15),
        ),
      ),
      child: Text(texto),
    );
  }
}
