config = {
    "_id": "rs0",
    "members": [
        {
            "_id": 0,
            "host": "mongo001:27017"
        },
        {
            "_id": 1,
            "host": "mongo002:27017"
        },
        {
            "_id": 2,
            "host": "mongo003:27017"
        }
    ]
}

rs.initiate(config);
db = db.getSiblingDB('gamestore')
db.createUser(
    {
        user: 'spring-boot',
        pwd: 'xyz123',
        roles: [{ role: 'readWrite', db: 'gamestore' }]
    }
);

