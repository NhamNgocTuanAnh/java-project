import React, { Component } from "react";
import TaskDataService from "../services/task.service";
import { Link } from "react-router-dom";
import Pagination from "@material-ui/lab/Pagination";
export default class TaskList extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.saveTask = this.saveTask.bind(this);
    this.newTask = this.newTask.bind(this);
    this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
    this.retrieveTasks = this.retrieveTasks.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveTask = this.setActiveTask.bind(this);
    this.removeAllTasks = this.removeAllTasks.bind(this);
    this.searchTitle = this.searchTitle.bind(this);
    this.handlePageChange = this.handlePageChange.bind(this);
    this.handlePageSizeChange = this.handlePageSizeChange.bind(this);
    this.updateComplete = this.updateComplete.bind(this);
    this.state = {
      tasks: [],

      id: null,
      title: "",
      content: "",
      complete: false,
      submitted: false,

      currentTask: {
        id: null,
        title: "",
        content: "",
        complete: false,
      },
      currentIndex: -1,
      searchTitle: "",

      page: 1,
      count: 0,
      pageSize: 5,
    };
    this.pageSizes = [5, 10, 15];
  }

  componentDidMount() {
    this.retrieveTasks();
  }

  onChangeSearchTitle(e) {
    const searchTitle = e.target.value;

    this.setState({
      searchTitle: searchTitle,
    });
  }

  retrieveTasks() {
    const { searchTitle, page, pageSize } = this.state;
    const params = this.getRequestParams(searchTitle, page, pageSize);

    TaskDataService.getAll(params)
      .then((response) => {
        const { tasks, totalPages } = response.data;

        this.setState({
          tasks: tasks,
          count: totalPages,
        });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }
  updateComplete(task, status) {
    var data = {
      id: task.id,
      title: task.title,
      content: task.content,
      complete: status,
    };

    TaskDataService.update(this.state.currentTask.id, data)
      .then((response) => {
        // this.setState((prevState) => ({
        //   currentTask: {
        //     ...prevState.currentTask,
        //     complete: status,
        //   },
        // }));
        this.retrieveTasks();
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  updateTask() {
    TaskDataService.update(this.state.currentTask.id, this.state.currentTask)
      .then((response) => {
        console.log(response.data);
        this.setState({
          message: "The task was updated successfully!",
        });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  getRequestParams(searchTitle, page, pageSize) {
    let params = {};

    if (searchTitle) {
      params["title"] = searchTitle;
    }

    if (page) {
      params["page"] = page - 1;
    }

    if (pageSize) {
      params["size"] = pageSize;
    }

    return params;
  }
  refreshList() {
    this.retrieveTasks();
    this.setState({
      currentTask: null,
      currentIndex: -1,
    });
  }

  setActiveTask(task, index) {
    this.setState({
      currentTask: task,
      currentIndex: index,
    });
  }

  removeAllTasks() {
    TaskDataService.deleteAll()
      .then((response) => {
        console.log(response.data);
        this.refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  searchTitle() {
    TaskDataService.findByTitle(this.state.searchTitle)
      .then((response) => {
        this.setState({
          tasks: response.data,
        });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }
  handlePageChange(event, value) {
    this.setState(
      {
        page: value,
      },
      () => {
        this.retrieveTasks();
      }
    );
  }

  handlePageSizeChange(event) {
    this.setState(
      {
        pageSize: event.target.value,
        page: 1,
      },
      () => {
        this.retrieveTasks();
      }
    );
  }

  onChangeTitle(e) {
    this.setState({
      title: e.target.value,
    });
  }

  saveTask = () => {
    var data = {
      id: this.state.id,
      title: this.state.title,
      content: this.state.content,
    };
    console.log(data);
    TaskDataService.create(data)
      .then((response) => {
        this.setState({
          id: response.data.id,
          title: response.data.title,
          content: response.data.content,
          // complete: response.data.complete,
          submitted: true,
        });

        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  newTask() {
    this.setState({
      id: null,
      title: "",
      description: "",
      published: false,

      submitted: false,
    });
  }
  render() {
    const {
      searchTitle,
      tasks,
      currentTask,
      currentIndex,
      page,
      count,
      pageSize,
    } = this.state;
    return (
      <div className="list row">
        <div className="col-md-8">
          <form>
            <div className="form-group submit-form">
              {this.state.submitted ? (
                <div>
                  <h4>You submitted successfully!</h4>
                  <button className="btn btn-success" onClick={this.newTask}>
                    Add new task
                  </button>
                </div>
              ) : (
                <div>
                  <h4>Task Quick Add</h4>
                  <div className="form-group">
                    <label htmlFor="title">Title</label>
                    <input
                      type="text"
                      className="form-control"
                      id="title"
                      required
                      value={this.state.title}
                      onChange={this.onChangeTitle}
                      name="title"
                    />
                  </div>

                  <button onClick={this.saveTask} className="btn btn-success">
                    Submit
                  </button>
                </div>
              )}
            </div>
          </form>

          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by title"
              value={searchTitle}
              onChange={this.onChangeSearchTitle}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.retrieveTasks}
              >
                Search
              </button>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <h4>Tasks List</h4>

          <div className="mt-3">
            {"Items per Page: "}
            <select onChange={this.handlePageSizeChange} value={pageSize}>
              {this.pageSizes.map((size) => (
                <option key={size} value={size}>
                  {size}
                </option>
              ))}
            </select>

            <Pagination
              className="my-3"
              count={count}
              page={page}
              siblingCount={1}
              boundaryCount={1}
              variant="outlined"
              shape="rounded"
              onChange={this.handlePageChange}
            />
          </div>

          <ul className="list-group">
            {this.state.tasks &&
              this.state.tasks.map((task, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setActiveTask(task, index)}
                  key={index}
                >
                  <div style={{ float: "left" }}>
                    <p> {task.title}</p>
                  </div>

                  <div className="button-control">
                    {task.complete ? (
                      <button
                        className="badge badge-primary mr-2"
                        onClick={() => this.updateComplete(task, false)}
                      >
                        Done
                      </button>
                    ) : (
                      <button
                        className="badge mr-2 btn-danger"
                        onClick={() => this.updateComplete(task, true)}
                      >
                        Doing
                      </button>
                    )}
                  </div>
                </li>
              ))}
          </ul>

          <button
            className="m-3 btn btn-sm btn-danger"
            onClick={this.removeAllTasks}
          >
            Remove All
          </button>
        </div>
        <div className="col-md-6">
          {this.state.currentTask ? (
            <div>
              <h4>Task</h4>
              <div>
                <label>
                  <strong>Title:</strong>
                </label>{" "}
                {this.state.currentTask.title}
              </div>
              <div>
                <label>
                  <strong>Content:</strong>
                </label>{" "}
                {this.state.currentTask.content}
              </div>
              <div>
                <label>
                  <strong>Status:</strong>
                </label>{" "}
                {this.state.currentTask.complete ? "Done" : "Doing"}
              </div>

              <Link
                to={"/tasks/" + this.state.currentTask.id}
                className="badge badge-warning"
              >
                Edit
              </Link>
            </div>
          ) : (
            <div>
              <br />
              <p>Please click on a Task...</p>
            </div>
          )}
        </div>
      </div>
    );
  }
}
