import React, { Component } from "react";
import TaskDataService from "../services/task.service";

export default class Task extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeContent = this.onChangeContent.bind(this);
    this.getTask = this.getTask.bind(this);
    this.updateComplete = this.updateComplete.bind(this);
    this.updateTask = this.updateTask.bind(this);
    this.deleteTask = this.deleteTask.bind(this);

    this.state = {
        currentTask: {
        id: null,
        title: "",
        content: "",
        todolist_id:null,
        complete: false
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getTask(this.props.match.params.id);
  }

  onChangeTitle(e) {
    const title = e.target.value;

    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTask,
          title: title
        }
      };
    });
  }

  onChangeContent(e) {
    const content = e.target.value;
    
    this.setState(prevState => ({
      currentTask: {
        ...prevState.currentTask,
        content: content
      }
    }));
  }

  getTask(id) {
    TaskDataService.get(id)
      .then(response => {
        this.setState({
          currentTask: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updateComplete(status) {
    var data = {
      id: this.state.currentTask.id,
      title: this.state.currentTask.title,
      content: this.state.currentTask.content,
      published: status
    };

    TaskDataService.update(this.state.currentTask.id, data)
      .then(response => {
        this.setState(prevState => ({
            currentTask: {
            ...prevState.currentTask,
            published: status
          }
        }));
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updateTask() {
    TaskDataService.update(
      this.state.currentTask.id,
      this.state.currentTask
    )
      .then(response => {
        console.log(response.data);
        this.setState({
          message: "The tutorial was updated successfully!"
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  deleteTask() {    
    TaskDataService.delete(this.state.currentTask.id)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/tasks')
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { currentTask } = this.state;

    return (
      <div>
        {currentTask ? (
          <div className="edit-form">
            <h4>Task</h4>
            <form>
              <div className="form-group">
                <label htmlFor="title">Title</label>
                <input
                  type="text"
                  className="form-control"
                  id="title"
                  value={currentTask.title}
                  onChange={this.onChangeTitle}
                />
              </div>
              <div className="form-group">
                <label htmlFor="content">Content</label>
                <input
                  type="text"
                  className="form-control"
                  id="content"
                  value={currentTask.content}
                  onChange={this.onChangeContent}
                />
              </div>

              <div className="form-group">
                <label>
                  <strong>Status:</strong>
                </label>
                {currentTask.complete ? "Published" : "Pending"}
              </div>
            </form>

            {currentTask.complete ? (
              <button
                className="badge badge-primary mr-2"
                onClick={() => this.updateComplete(false)}
              >
                UnPublish
              </button>
            ) : (
              <button
                className="badge badge-primary mr-2"
                onClick={() => this.updateComplete(true)}
              >
                Publish
              </button>
            )}

            <button
              className="badge badge-danger mr-2"
              onClick={this.deleteTask}
            >
              Delete
            </button>

            <button
              type="submit"
              className="badge badge-success"
              onClick={this.updateTask}
            >
              Update
            </button>
            <p>{this.state.message}</p>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Task...</p>
          </div>
        )}
      </div>
    );
  }
}
