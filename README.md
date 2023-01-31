# Getting Started

## Test w/ graphiql
http://localhost:8080/graphiql?path=/graphql

Sample input
```
{
  allBooks {
    isbn
    title
    author {
      lastName
    }
  }
}
```

## Test w/ httpie
Sample commands
```shell
http :8080/graphql query='{booksByAuthorLastName(lastName: "Smith"){isbn title}}'
http :8080/graphql query='{allBooks{title author{lastName}}}'
```