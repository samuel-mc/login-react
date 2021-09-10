import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";
import { Home } from "./pages/Home";
import './App.css';
import { LoginContainer } from './containers/LoginContainer'

function App() {

  const token = document.cookie.split('=')[1];

  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/" exact>
            {!token ? <Redirect to="/login" /> : <Home />}
          </Route>
          <Route path="/login" exact component={() => <LoginContainer />} />
        </Switch>
      </Router>
      {/* <LoginContainer /> */}
    </div>
  );
}

export default App;
