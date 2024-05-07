import 'package:flutter/material.dart';

void main() {
  runApp(const App1());
}

class App1 extends StatelessWidget {
  const App1({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
            title: const Text("First Flutter Application"),
            backgroundColor: Theme.of(context).colorScheme.primary),
        body: const Center(
          child: Text("Hello World!"),
        ),
      ),
    );
  }
}
