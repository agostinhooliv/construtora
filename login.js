var mysql = require('mysql');
var express = require('express');
var app = express();

//Cria e inicia a conexão
var connection = mysql.createConnection({
     host: 'localhost',
     user: 'root',
     password : 'root',
     port : 3306, //port mysql
     database:'construtora'
});

var bodyParser = require('body-parser')
app.use(bodyParser.json());       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
})); 

//url para verificação de login
app.post('/login', function(req, res) {
 var data = {
        login: req.body.login, 
	senha: req.body.senha,
	ambienteTestes: req.body.ambienteTestes
    };
	
	connection.query('SELECT de_logi, de_senh from usuario where de_logi = ? and de_senh = ?', [data.login, data.senha], function(err, rows, fields) {
  	    if (err) throw err;
 	  
	    if (rows.length > 0) {
  	     console.log({status:true});
  	     return res.status(200).json({status:true});		 
	    } else {
              console.log({status:false});
              return res.status(200).json({status:false});		
	    }
	  });	
});

//url para inserir novas despesas
app.post('/insert_despesas_gerais', function(req, res) {
 var data = {
        dataDespesa: req.body.dataDespesa,
        observacao: req.body.observacao,
        tipoDespesa: req.body.tipoDespesa,
        valorDespesa: req.body.valorDespesa,
        dataVencimento: req.body.dataVencimento
    };
        //Tabela despesas
        sql = "Insert into despesas(dt_desp, sg_stat, de_obs) values('"+data.dataDespesa+"', 'A', '"+data.observacao+"')";
        connection.query(sql, function(err, rows, fields) {
                if (err) {
                  console.log({status:false});
                } else {
                  console.log({status:true});
                }
        });

        //Tabela despesas_geral
        sql = "Insert into despesas_geral(despesas_idDespesas, tp_desp, vr_desp, dt_venc) values(LAST_INSERT_ID(), '"+data.tipoDespesa+"', '"+data.valorDespesa+"', '"+data.dataVencimento+"')";
        connection.query(sql, function(err, rows, fields) {
                 if (err) {
                  console.log({status:false});
                  return res.status(200).json({status:false});
                } else {
                  console.log({status:true});
                  return res.status(200).json({status:true});
                }
        });
});
 
app.listen(3000);
console.log('Servidor iniciado em localhost:3000. Ctrl+C para encerrar…');
