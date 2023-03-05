import 'dart:convert';

import 'package:http/http.dart' as http;

import 'package:flutter/material.dart';

/// This example shows how to use the http package to fetch data from a REST API.
class RestApiFetch extends StatefulWidget {
  const RestApiFetch({Key? key}) : super(key: key);

  @override
  _RestApiFetchState createState() => _RestApiFetchState();
}

/// This is the private State class that goes with MyStatefulWidget.
class _RestApiFetchState extends State<RestApiFetch> {
  late TextEditingController _urlController;
  late TextEditingController _apiTokenController;
  String _responseBody = '<empty>';
  String _error = '<none>';
  bool _pending = false;

  @override
  void initState() {
    super.initState();
    _urlController = TextEditingController(
        text: 'https://jsonplaceholder.typicode.com/todos/1');
    _apiTokenController = TextEditingController(text: '');
  }

  // Reset the state of the app.
  void _reset({bool resetControllers = true}) {
    setState(() {
      if (resetControllers) {
        _urlController.text = 'https://jsonplaceholder.typicode.com/todos/1';
        _apiTokenController.text = '';
      }
      _responseBody = '<empty>';
      _error = '<none>';
      _pending = false;
    });
  }

  // Using the http package we can easily GET data from REST APIs.
  // https://x-wei.github.io/flutter_catalog/#/minified:Ry
  // TODO: Add a POST example.
  Future<void> _httpGet(String url, String apiToken) async {
    _reset();
    setState(() {
      _pending = true;
    });
    try {
      final http.Response response = await http.get(Uri.parse(url));
      if (response.statusCode == 200) {
        setState(() {
          _responseBody = response.body;
        });
      } else {
        setState(() {
          _error = response.body;
        });
      }
    } catch (e) {
      setState(() {
        _error = e.toString();
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    throw UnimplementedError();
  }
}
