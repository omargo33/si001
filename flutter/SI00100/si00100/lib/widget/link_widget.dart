import 'package:flutter/material.dart';
import 'package:si00100/common/app_colors.dart';

class LinkWidget extends StatelessWidget {
  const LinkWidget({Key? key, this.onClick, this.text = 'Pendiente'})
      : super(key: key);

  final String text;
  final VoidCallback? onClick;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onClick,
      child: Text(
        text,
        style: TextStyle(
          color: AppColors.blackCoralMC,
          fontSize: 14,
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }
}
