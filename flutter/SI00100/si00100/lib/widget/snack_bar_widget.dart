import 'package:flutter/cupertino.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:si00100/common/app_colors.dart';

/// Clase para el snackbar personalizado
///
class SnackBarWidget extends StatelessWidget {
  const SnackBarWidget({
    Key? key,
    required this.titulo,
    required this.subtitulo,
    required this.icono,
  }) : super(key: key);

  final String titulo;
  final String subtitulo;
  final String icono;

  @override
  Widget build(BuildContext context) {
    return Stack(
      clipBehavior: Clip.none,
      children: [
        Container(
          height: 90,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(25.0),
            color: AppColors.spaceCadetMC[700]!,
          ),
          child: Row(
            children: [
              const SizedBox(width: 10),
              SvgPicture.asset(
                icono,
                height: 80,
                width: 80,
                color: AppColors.whiteMC[700],
              ),
              const SizedBox(width: 5),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const SizedBox(height: 17),
                    Text(
                      titulo,
                      style: TextStyle(
                        color: AppColors.white,
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(
                      subtitulo,
                      style: TextStyle(
                        color: AppColors.whiteMC[700],
                        fontSize: 14,
                      ),
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                    ),
                  ],
                ),
              ),
              const SizedBox(width: 10),
            ],
          ),
        ),
      ],
    );
  }
}
