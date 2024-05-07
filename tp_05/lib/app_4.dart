import 'package:flutter/material.dart';

void main() {
  runApp(const App4());
}

class App4 extends StatelessWidget {
  const App4({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
            title: const Text("Fourth Flutter Application"),
            backgroundColor: Theme.of(context).colorScheme.primary),
        body: Center(
            child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              width: 350,
              child: Image.asset(
                'images/tunis.jpg',
                fit: BoxFit.fill,
              ),
            ),
            const Text(
              'Hello World!',
            ),
          ],
        )),
      ),
    );
  }
}
