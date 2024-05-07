import 'package:flutter/material.dart';

void main() {
  runApp(App5());
}

class App5 extends StatelessWidget {
  final List Liste = List.generate(1000, (index) {
    return {
      "id": index,
      "title": "This is the title $index",
      "subtitle": "This is the subtitle $index"
    };
  });

  App5({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Fifth Flutter Application'),
          backgroundColor: Theme.of(context).colorScheme.primary,
        ),
        body: ListView.builder(
          itemCount: Liste.length,
          itemBuilder: (context, index) => Card(
            elevation: 6,
            margin: const EdgeInsets.all(10),
            child: ListTile(
              leading: CircleAvatar(
                backgroundColor: Colors.purple,
                child: Text(Liste[index]["id"].toString()),
              ),
              title: Text(Liste[index]["title"]),
              subtitle: Text(Liste[index]["subtitle"]),
              trailing: const Icon(Icons.add_link),
            ),
          ),
        ),
      ),
    );
  }
}
