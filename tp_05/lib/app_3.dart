import 'package:flutter/material.dart';

void main() {
  runApp(const App3());
}

class App3 extends StatelessWidget {
  const App3({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
            title: const Text("Third Flutter Application"),
            backgroundColor: Theme.of(context).colorScheme.primary),
        body: Center(
            child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Image.asset('images/tunis.jpg'),
            const Text(
              'Hello World!',
            ),
          ],
        )),
      ),
    );
  }
}
