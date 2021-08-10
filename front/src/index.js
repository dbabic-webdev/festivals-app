import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import { Route, Link, HashRouter as Router, Switch } from "react-router-dom";
import { Container, Navbar, Nav, Button, Form } from "react-bootstrap";
import NotFound from "./components/NotFound";
import { logout } from "./services/auth";
import Login from "./components/Login/Login";
import Home from "./components/Home";
import Festivali from "./components/Festivali/Festivali";
import Add from "./components/Festivali/Add";
import Reservation from "./components/Festivali/Rezervisi";
import Edit from "./components/Festivali/Edit";

class App extends React.Component {
  render() {
    return (
      <div>
        <Router>
          <Navbar bg="dark" variant="dark" expand>
            <Navbar.Brand as={Link} to="/">
              Festivals-App
            </Navbar.Brand>
            {/*className="mr-auto" podesi ovu grupu Nav Link-ova da se "rasire" sto je vise moguce,
              i zbog toga je dugme Log in/Log out skroz sa leve strane*/}
            <Nav className="mr-auto">
              <Nav.Link as={Link} to="/festivali">
                Festivals
              </Nav.Link>
            </Nav>

            {window.localStorage["jwt"] ? (
              <Button onClick={() => logout()}>Log out</Button>
            ) : (
              <Nav.Link as={Link} to="/login">
                Log in
              </Nav.Link>
            )}
          </Navbar>
          <Container style={{ marginTop: 25 }}>
            <Switch>
              <Route exact path="/" component={Home} />
              <Route exact path="/festivali" component={Festivali} />
              <Route
                exact
                path="/festivali/rezervisi/:id"
                component={Reservation}
              />
              <Route exact path="/festivali/add" component={Add} />
              <Route exact path="/festivali/edit/:id" component={Edit} />
              <Route exact path="/login" component={Login} />
              <Route component={NotFound} />
            </Switch>
          </Container>
        </Router>
      </div>
    );
  }
}

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById("root")
);
