import React from 'react';
import ReactDOM from 'react-dom';
import Institucional from './Institucional';
import Cadastro from './Cadastro';
import Login from './Login';
import Home from './Home';
import Pedidos from './Pedidos';
import Entregadores from './Entregadores';
import { BrowserRouter, Switch, Route } from 'react-router-dom';



ReactDOM.render(
  <BrowserRouter>
    <Switch>
      <Route path="/" exact={true} component={Institucional} />
      <Route path="/cadastro" component={Cadastro} />
      <Route path="/login" component={Login} />
      <Route path="/home" component={Home} />
      <Route path="/pedidos" component={Pedidos} />
      <Route path="/entregadores" component={Entregadores} />
      <Route path="*" component={<div>404</div>} />
    </Switch>
  </BrowserRouter>
  , document.getElementById('root'));