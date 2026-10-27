import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// The dev server forwards these paths to Spring Boot, so the browser
// only talks to localhost:5173 and CORS is never an issue in development.
const BACKEND = 'http://localhost:8080';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/auth': { target: BACKEND, changeOrigin: true },
      '/api': { target: BACKEND, changeOrigin: true },
      '/admin': { target: BACKEND, changeOrigin: true },
    },
  },
});
