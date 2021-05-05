import React, { Component } from "react";
import TaskDataService from "../services/task.service";
import { Link } from "react-router-dom";
import Pagination from "@material-ui/lab/Pagination";
export default class TaskList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
    this.retrieveTasks = this.retrieveTasks.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveTask = this.setActiveTask.bind(this);
    this.removeAllTasks = this.removeAllTasks.bind(this);
    this.searchTitle = this.searchTitle.bind(this);
    this.handlePageChange = this.handlePageChange.bind(this);
    this.handlePageSizeChange = this.handlePageSizeChange.bind(this);

    this.state = {
      tasks: [],
      currentTask: null,
      currentIndex: -1,
      searchTitle: "",

      page: 1,
      count: 0,
      pageSize: 3,
    };
    this.pageSizes = [3, 6, 9];
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
    this.retrieveTutorials();
    this.setState({
      currentTutorial: null,
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
              {tasks &&
                tasks.map((task, index) => (
                  <li
                    className={
                      "list-group-item " +
                      (index === currentIndex ? "active" : "")
                    }
                    onClick={() => this.setActiveTask(task, index)}
                    key={index}
                  >
                    {task.title}
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
          {currentTask ? (
            <div>
              <h4>Task</h4>
              <div>
                <label>
                  <strong>Title:</strong>
                </label>{" "}
                {currentTask.title}
              </div>
              <div>
                <label>
                  <strong>Content:</strong>
                </label>{" "}
                {currentTask.content}
              </div>
              <div>
                <label>
                  <strong>Status:</strong>
                </label>{" "}
                {currentTask.complete ? "Published" : "Pending"}
              </div>

              <Link
                to={"/tasks/" + currentTask.id}
                className="badge badge-warning"
              >
                Edit
              </Link>
            </div>
          ) : (
            <div>
              <br />
              <p>Please click on a Tutorial...</p>
            </div>
          )}
          </div>
        
        </div>
      );
  }
}
