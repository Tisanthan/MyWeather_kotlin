import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';




void main()=>runApp(FlutterApp());


class Hello extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(50),
      child: Container(
        color: Colors.amberAccent,
        height: 200,
        width: 300,
        child: Center(
          child: Text(
            "hi tisanthan", style: TextStyle(fontSize: 50),
          ),
        ),
      ),
    );
  }
}

class FlutterApp extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
          appBar: AppBar(
            backgroundColor: Colors.deepOrangeAccent,
            title: Text("welcome"),
          ),
          body: Hello()
      ),
    );
  }

}