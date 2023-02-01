# Spring Demo Project

This is a demo Spring project that can do basic user management functions.

## Installation

This project uses Elasticsearch as persistence so in order to test, you need an Elasticsearch instance running, for example:

```bash
docker run -p 9200:9200 \
  -e "discovery.type=single-node" \
  docker.elastic.co/elasticsearch/elasticsearch:7.17.8
```

## Usage Example

```
POST http://localhost:8080/users
{
  "firstName" : "John",
  "lastName" : "Smith",
  "emailAddress" : "test2345@bla.com"
  }
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.
