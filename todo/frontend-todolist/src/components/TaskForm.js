import React, {  Component } from "react";
import TaskDataService from "../services/task.service";

export default class TaskForm extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeContent = this.onChangeContent.bind(this);
    this.saveTask = this.saveTask.bind(this);
    this.newTask = this.newTask.bind(this);
    this.state = {
      id: null,
      title: "",
      content: "",
      todolist_id: null,
      complete: false,
      submitted: false,
    };
  }

  onChangeTitle(e) {
    this.setState({
      title: e.target.value,
    });
  }

  onChangeContent(e) {
    this.setState({
      content: e.target.value,
    });
  }
  saveTask = () => {
    var data = {
      id: this.state.id,
      title: this.state.title,
      content: this.state.content,
      todolist_id: this.state.todolist_id,
    };
    console.log(data);
    TaskDataService.create(data)
      .then((response) => {
        this.setState({
          id: response.data.id,
          title: response.data.title,
          content: response.data.content,
          todolist_id: response.data.todolist_id,
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
      todolist_id: null,

      submitted: false,
    });
  }
  render() {
    return (
      <div className="submit-form">
        <div className="submit-form">
          {this.state.submitted ? (
            <div>
              <h4>You submitted successfully!</h4>
              <button className="btn btn-success" onClick={this.newTask}>
                Thêm task
              </button>
            </div>
          ) : (
            <div>
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

              <div className="form-group">
                <label htmlFor="content">Content</label>
                <input
                  type="text"
                  className="form-control"
                  id="content"
                  required
                  value={this.state.content}
                  onChange={this.onChangeContent}
                  name="content"
                />
              </div>

              <button onClick={this.saveTask} className="btn btn-success">
                Submit
              </button>
            </div>
          )}
        </div>
      </div>
    );
  }
}
