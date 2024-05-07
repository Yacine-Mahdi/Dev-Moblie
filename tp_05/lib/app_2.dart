import 'package:flutter/material.dart';

void main() {
  runApp(const App2());
}

class App2 extends StatelessWidget {
  const App2({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
            title: const Text("Second Flutter Application"),
            backgroundColor: Theme.of(context).colorScheme.primary),
        body: Center(
            child: Container(
          padding: const EdgeInsets.all(20),
          decoration: BoxDecoration(
              border: Border.all(
                color: Colors.black,
                width: 8,
              ),
              borderRadius: BorderRadius.circular(12)),
          child: const Text(
            'Hello World!',
            style: TextStyle(color: Colors.deepPurpleAccent),
          ),
        )),
      ),
    );
  }
}
