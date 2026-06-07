const express = require('express');
const path = require('path');
const app = express();

app.use(express.static(path.join(__dirname, 'dist/aec-platform/browser')));

app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'dist/aec-platform/browser/index.html'));
});

const PORT = process.env.PORT || 4200;
app.listen(PORT, () => console.log(`Server on port ${PORT}`));
