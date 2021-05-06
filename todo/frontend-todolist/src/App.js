import React, { Component } from "react";

import TaskForm from "./components/TaskForm";
import TaskList from "./components/TaskList";
import Task from "./components/Task";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import "./App.css";
class App extends Component {
  render() {
    return (  <div className="body">
      <Router>
      
          <nav className="navbar navbar-expand navbar-dark bg-dark">
            <a href="/tasks" className="navbar-brand">
              Todo App
            </a>
            <div className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to={"/tasks"} className="nav-link">
                  Tasks
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/add"} className="nav-link">
                  Add new task
                </Link>
              </li>
            </div>
          </nav>

          <div className="container mt-3 ">
        
            <Switch>
              <Route exact path={["/", "/tasks"]} component={TaskList} />
              <Route exact path="/add" component={TaskForm} />
              <Route path="/tasks/:id" component={Task} />
            </Switch>
          </div>
       
      </Router> </div>
    );
  }
}
export default App;
