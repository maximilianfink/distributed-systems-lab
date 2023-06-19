<template>
  <div class="container">
    <h1 class="mb-4">Todos</h1>
    <br><br>
    <div v-for="todo in todos" :key="todo.id" class="d-flex align-items-center mb-2">
      <span :class="{ 'done': todo.done }" class="flex-grow-1">{{ todo.title }}</span>
      <span class="status" :class="{ 'done': todo.done }">{{ getStatus(todo.done) }}</span>
      <button @click="deleteTodo(todo.id)" class="btn btn-danger mx-2">Delete</button>
      <button @click="editTodo(todo)" class="btn btn-primary mx-2">Edit</button>
      <button v-if="!todo.done" @click="markDone(todo.id)" class="btn btn-success mx-2">Mark Done</button>
    </div>
    <br><br>
    <form @submit.prevent="addTodo" class="mb-4">
      <div class="input-group">
        <input type="text" v-model="newTodo" placeholder="New Todo" class="form-control" />
        <button type="submit" class="btn btn-primary">Add Todo</button>
      </div>
    </form>
    <div v-if="editingTodo" class="mb-4">
      <input type="text" v-model="editedTodo.title" placeholder="Edit Todo" class="form-control" />
      <button @click="updateTodo" class="btn btn-primary mx-2">Save</button>
      <button @click="cancelEdit" class="btn btn-secondary mx-2">Cancel</button>
    </div>
  </div>
  <br><br>
  <p style="text-align: center; font-style: italic;">Distributed Systems Lab by Maximilian Fink - <a href="https://github.com/maximilianfink/distributed-systems-lab">Code on GitHub</a></p>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      todos: [],
      newTodo: '',
      backendUrl: process.env.BACKEND_URL || 'http://localhost:8080',
      editingTodo: null,
      editedTodo: {
        id: '',
        title: '',
        done: false
      }
    };
  },
  created() {
    this.fetchTodos();
  },
  methods: {
    fetchTodos() {
      axios.get(`${this.backendUrl}/api/todos`)
        .then(response => {
          this.todos = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    addTodo() {
      axios.post(`${this.backendUrl}/api/todos?title=${encodeURIComponent(this.newTodo)}`)
        .then(() => {
          this.fetchTodos();
          this.newTodo = '';
        })
        .catch(error => {
          console.error(error);
        });
    },
    deleteTodo(todoId) {
      axios.delete(`${this.backendUrl}/api/todos/${todoId}`)
        .then(() => {
          this.fetchTodos();
        })
        .catch(error => {
          console.error(error);
        });
    },
    editTodo(todo) {
      this.editingTodo = todo.id;
      this.editedTodo.id = todo.id;
      this.editedTodo.title = todo.title;
      this.editedTodo.done = todo.done;
    },
    updateTodo() {
      axios.put(`${this.backendUrl}/api/todos/${this.editedTodo.id}`, this.editedTodo)
        .then(() => {
          this.fetchTodos();
          this.cancelEdit();
        })
        .catch(error => {
          console.error(error);
        });
    },
    cancelEdit() {
      this.editingTodo = null;
      this.editedTodo.id = '';
      this.editedTodo.title = '';
      this.editedTodo.done = false;
    },
    getStatus(done) {
      return done ? 'Done' : 'Open';
    },
    markDone(todoId) {
      const todo = this.todos.find(todo => todo.id === todoId);
      axios.put(`${this.backendUrl}/api/todos/${todoId}`, { title: todo.title, done: true })
        .then(() => {
          this.fetchTodos();
        })
        .catch(error => {
          console.error(error);
        });
    }
  }
};
</script>

<style scoped>
.done {
  text-decoration: line-through;
}
.status {
  margin-left: 10px;
}
</style>
