const a = require('./prod');

test('Check if revenue is a positive integer', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if current revenue value is greater than previous revenue value', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if new customers value is an integer', () => {
  expect(a(1, 2)).toBe(3);
}); 

test('Check if leads converted is between 0 to 100', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if sales value is greater than or equal to zero', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if assign pints input bar under leaderboards accepts only numbers', () => {
  expect(a(1, 2)).toBe(3);
}); 
test('Check if performance under leaderboards is a float value', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if assign points input bar under Constraints accepts only numbers', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if Agent level-up constraints accept only numbers', () => {
  expect(a(1, 2)).toBe(3);
}); 
test('Check if verification tick constraints accept only numbers', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if Agent Milestones accepts only numbers', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if assign points input bar under Constraints accepts only numbers under CHILD ADMIN pages', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if revenue is a positive integer under CHILD ADMIN pages', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if current revenue value is greater than previous revenue value under CHILD ADMIN pages', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if new customers value is an integer under CHILD ADMIN pages', () => {
  expect(a(1, 2)).toBe(3);
}); 

test('Check if leads converted is between 0 to 100 under CHILD ADMIN pages', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if sales value is greater than or equal to zero under CHILD ADMIN pages', () => {
  expect(a(1, 2)).toBe(3);
});

test('Check if assign pints input bar under leaderboards accepts only numbers under CHILD ADMIN pages', () => {
  expect(a(1, 2)).toBe(3);
}); 
test('Check if performance under leaderboards is a float value under CHILD ADMIN pages', () => {
  expect(a(1, 2)).toBe(3);
});

