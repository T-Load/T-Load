interface ButtonProps {
  children: React.ReactNode;
}

const Button = ({ children }: ButtonProps) => {
  return <button className="flex-center">{children}</button>;
};

export default Button;
