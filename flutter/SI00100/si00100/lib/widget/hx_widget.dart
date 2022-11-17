import 'package:flutter/material.dart';
import 'package:si00100/common/app_colors.dart';

/// Clase h1 h2 h3 widget
class HxWidget extends StatelessWidget {
  final int indice;
  final String texto;

  const HxWidget({
    Key? key,
    required this.indice,
    required this.texto,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    int valorOpacidadCalculado = 1000 - (indice * 100);
    double fontSizeCalcuado = 35 - (indice * 5);
    double paddinCalculado = 10 - indice.toDouble();
    return Container(
      padding: EdgeInsets.symmetric(vertical: paddinCalculado),
      child: Row(mainAxisAlignment: MainAxisAlignment.start, children: [
        Text(texto,
            maxLines: 4,
            overflow: TextOverflow.ellipsis,
            textDirection: TextDirection.ltr,
            //textAlign: TextAlign.left,
            style: TextStyle(
                color: AppColors.blackCoralMC[valorOpacidadCalculado],
                fontSize: fontSizeCalcuado,
                fontWeight: FontWeight.bold)),
      ]),
    );
  }
}
