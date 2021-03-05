import * as jwt from 'jsonwebtoken';


const jwt = require ('jsonwebtoken')
const bodyParser = require('body-parser');

app.use(bodyParser.json());

    app.post('login', function(req, res) {
        const body = req.body;
      
        const user = USER.find(user => user.username == body.username);
        if(!user || body.password != 'everything') return res.sendStatus(401);
        
        var token = jwt.sign({userID: user.id}, 'app-super-shared-secret', {expiresIn: '2h'});
        res.send({token});
      });